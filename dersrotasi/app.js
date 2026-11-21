const menuButton = document.querySelector('.menu-toggle');
const nav = document.querySelector('.main-nav');

menuButton?.addEventListener('click', () => {
  const isOpen = menuButton.getAttribute('aria-expanded') === 'true';
  menuButton.setAttribute('aria-expanded', String(!isOpen));
  menuButton.setAttribute('aria-label', isOpen ? 'Menüyü aç' : 'Menüyü kapat');
  nav?.classList.toggle('is-open', !isOpen);
});

nav?.querySelectorAll('a').forEach((link) => link.addEventListener('click', () => {
  menuButton?.setAttribute('aria-expanded', 'false');
  menuButton?.setAttribute('aria-label', 'Menüyü aç');
  nav.classList.remove('is-open');
}));

document.querySelector('#year').textContent = new Date().getFullYear();

const form = document.querySelector('#inquiry-form');
const result = document.querySelector('#draft-result');
const messageField = document.querySelector('#draft-message');
const copyButton = document.querySelector('#copy-message');

form?.addEventListener('submit', (event) => {
  event.preventDefault();
  if (!form.reportValidity()) return;
  const data = new FormData(form);
  const name = String(data.get('name')).trim();
  const level = String(data.get('level') || '').trim();
  const subject = String(data.get('subject')).trim();
  const goal = String(data.get('goal') || '').trim();
  const lines = [
    `Merhaba, ben ${name}.`,
    level ? `${level} düzeyinde öğrenim görüyorum.` : '',
    `${subject} dersi hakkında ön görüşme yapmak istiyorum.`,
    goal ? `Hedefim / merak ettiğim: ${goal}` : '',
  ].filter(Boolean);
  messageField.value = lines.join('\n');
  result.hidden = false;
  copyButton.textContent = 'Mesajı kopyala ⧉';
  result.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
});

copyButton?.addEventListener('click', async () => {
  try {
    await navigator.clipboard.writeText(messageField.value);
    copyButton.textContent = 'Kopyalandı ✓';
  } catch {
    messageField.focus();
    messageField.select();
    copyButton.textContent = 'Metni seçip kopyala';
  }
});
